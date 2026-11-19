(function () {
    let nextQuizId = 0;

    function initializeQuizzes(root = document) {
        const quizzes = [];
        if (root.matches?.("quiz"))
            quizzes.push(root);
        quizzes.push(...root.querySelectorAll("quiz"));

        quizzes.forEach(quiz => {
            const options = Array.from(quiz.querySelectorAll(":scope > option"))
                .map(option => ({
                    text: option.textContent.trim(),
                    correct: option.hasAttribute("correct")
                }));
            const question = quiz.getAttribute("question")?.trim();
            const correctCount = options.filter(option => option.correct).length;
            const error = !question
                ? "A question is required."
                : !quiz.id.trim()
                    ? "A stable quiz id is required."
                : options.length < 2
                    ? "At least two options are required."
                    : options.some(option => !option.text)
                        ? "Options cannot be empty."
                        : correctCount !== 1
                            ? "Exactly one option must be marked correct."
                            : null;

            if (error) {
                const message = document.createElement("p");
                message.className = "quiz-error";
                message.setAttribute("role", "alert");
                message.textContent = `Quiz configuration error: ${error}`;
                quiz.replaceWith(message);
                console.error(`Invalid quiz: ${error}`, quiz);
                return;
            }

            const id = `ebook-quiz-${nextQuizId++}`;
            const storageKey = `matte:quiz:${quiz.id.trim()}`;
            const savedAnswer = loadAnswer(storageKey, options.length);
            const form = document.createElement("form");
            form.className = "ebook-quiz";
            const optionLabels = [];
            const fieldset = document.createElement("fieldset");
            const legend = document.createElement("legend");
            legend.textContent = question;
            fieldset.append(legend);

            options.forEach((option, index) => {
                const label = document.createElement("label");
                label.className = "quiz-option";
                const input = document.createElement("input");
                input.type = "radio";
                input.name = id;
                input.value = String(index);
                input.required = true;
                if (savedAnswer === index)
                    input.checked = true;
                label.append(input, document.createTextNode(` ${option.text}`));
                fieldset.append(label);
                optionLabels.push(label);
            });

            const submit = document.createElement("button");
            submit.type = "submit";
            submit.textContent = "Verificar resposta";
            const feedback = document.createElement("p");
            feedback.className = "quiz-feedback";
            feedback.setAttribute("role", "status");
            feedback.setAttribute("aria-live", "polite");

            form.append(fieldset, submit, feedback);
            form.addEventListener("change", () => {
                const answer = form.querySelector(`input[name="${id}"]:checked`);
                if (answer)
                    saveAnswer(storageKey, answer.value);
                feedback.textContent = "";
                feedback.classList.remove("is-correct", "is-incorrect");
                optionLabels.forEach(label => label.classList.remove("is-correct", "is-incorrect"));
            });
            const validateAnswer = () => {
                const answer = form.querySelector(`input[name="${id}"]:checked`);
                if (!answer) {
                    feedback.textContent = "Escolhe uma resposta.";
                    return;
                }
                const isCorrect = options[Number(answer.value)].correct;
                const selectedLabel = optionLabels[Number(answer.value)];
                optionLabels.forEach(label => label.classList.remove("is-correct", "is-incorrect"));
                selectedLabel.classList.add(isCorrect ? "is-correct" : "is-incorrect");
                feedback.textContent = isCorrect ? "Correto!" : "Ainda não. Tenta novamente.";
                feedback.classList.toggle("is-correct", isCorrect);
                feedback.classList.toggle("is-incorrect", !isCorrect);
            };
            form.addEventListener("submit", event => {
                event.preventDefault();
                validateAnswer();
            });
            quiz.replaceWith(form);
            if (savedAnswer !== null)
                validateAnswer();
        });
    }

    function loadAnswer(key, optionCount) {
        try {
            const answer = window.localStorage.getItem(key);
            if (answer === null || !/^\d+$/.test(answer))
                return null;
            const index = Number(answer);
            return index < optionCount ? index : null;
        } catch (error) {
            console.warn("Unable to load saved quiz answer:", error);
            return null;
        }
    }

    function saveAnswer(key, answer) {
        try {
            window.localStorage.setItem(key, answer);
        } catch (error) {
            console.warn("Unable to save quiz answer:", error);
        }
    }

    window.initializeQuizzes = initializeQuizzes;
})();
