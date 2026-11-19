function testcases_matte(editor, childTags) {
    let div = document.createElement("div");
    div.style = "margin-top: .5em; margin-bottom: .5em; margin-left: .5em; margin-right: .5em;";

    const table = document.createElement("table");
    table.style.cssText = "width: 100%; border-collapse: collapse; font-family: inherit;";
    const header = table.createTHead().insertRow();
    ["", "Call", "Expected", "Result"].forEach(text => {
        const cell = document.createElement("th");
        cell.textContent = text;
        cell.style.cssText = "padding: .4em .6em; border: 1px solid #ccc; background: #f2f2f2; text-align: left;";
        header.append(cell);
    });
    const body = table.createTBody();
    div.append(table);

    const cases = [];
    const styleEditableInput = input => {
        input.style.cssText = "box-sizing: border-box; width: 100%; padding: 0; border: 1px solid transparent; background: transparent; color: inherit; font: inherit;";
        input.addEventListener("focus", () => {
            input.style.borderColor = "#888";
            input.style.backgroundColor = "white";
        });
        input.addEventListener("blur", () => {
            input.style.borderColor = "transparent";
            input.style.backgroundColor = "transparent";
        });
    };

    const appendCaseRow = (call, expected, editable = false) => {
        const row = body.insertRow();
        const actionCell = row.insertCell();
        const callCell = row.insertCell();
        const expectedCell = row.insertCell();
        const resultCell = row.insertCell();
        [callCell, expectedCell, resultCell, actionCell].forEach(cell => {
            cell.style.cssText = "padding: .4em .6em; border: 1px solid #ccc;";
        });
        actionCell.style.textAlign = "center";

        let callInput;
        let expectedInput;
        if (editable) {
            callInput = document.createElement("input");
            callInput.type = "text";
            callInput.value = call;
            callInput.placeholder = "Call";
            styleEditableInput(callInput);
            callCell.append(callInput);

            expectedInput = document.createElement("input");
            expectedInput.type = "text";
            expectedInput.value = expected;
            expectedInput.placeholder = "Expected";
            styleEditableInput(expectedInput);
            expectedInput.addEventListener("blur", () => runCase(testCase));
            expectedCell.append(expectedInput);
        } else {
            const callParts = call.match(/^([A-Za-z_$][\w$]*)(.*)$/);
            if (callParts) {
                const callLink = document.createElement("a");
                callLink.href = "#";
                callLink.textContent = callParts[1];
                callLink.title = "Load call into the editor";
                callLink.addEventListener("click", event => {
                    event.preventDefault();
                    if (editor.callBox) {
                        editor.callBox.value = call;
                        editor.callBox.dispatchEvent(new Event("input", { bubbles: true }));
                        editor.callBox.focus();
                    }
                });
                callCell.append(callLink, document.createTextNode(callParts[2]));
            } else {
                callCell.textContent = call;
            }
            expectedCell.textContent = expected;
        }

        const resultText = document.createElement("span");
        const testCase = { row, call, expected, callInput, expectedInput, resultText };
        resultCell.append(resultText);
        if (editable) {
            const deleteButton = document.createElement("button");
            deleteButton.type = "button";
            deleteButton.textContent = "-";
            deleteButton.setAttribute("aria-label", "Delete row");
            deleteButton.title = "Delete row";
            deleteButton.addEventListener("click", () => {
                cases.splice(cases.indexOf(testCase), 1);
                row.remove();
            });
            actionCell.append(deleteButton);
        }
        cases.push(testCase);
        return testCase;
    };

    const runCase = testCase => {
        const call = testCase.callInput ? testCase.callInput.value : testCase.call;
        const expected = testCase.expectedInput ? testCase.expectedInput.value : testCase.expected;
        const result = editor.runParserStandalone(call);
        const resultText = result.result ? editor.prettyPrint(result.result.evaluation) : "call error";
        testCase.resultText.innerHTML = resultText;
        const toJsonValue = value => {
            if (Array.isArray(value))
                return value.map(toJsonValue);
            if (value && typeof value === "object") {
                return Object.fromEntries(
                    Object.entries(value)
                        .filter(([key]) => key !== "$type")
                        .map(([key, field]) => [
                            key,
                            toJsonValue(field && typeof field === "object" && "evaluation" in field
                                ? field.evaluation
                                : field)
                        ])
                );
            }
            return value;
        };
        let expectedValue;
        try {
            expectedValue = JSON.parse(expected);
        } catch {
            testCase.row.style.backgroundColor = "#ffebee";
            return;
        }
        const matches = result.result &&
            JSON.stringify(toJsonValue(result.result.evaluation)) === JSON.stringify(expectedValue);
        testCase.row.style.backgroundColor = matches ? "#e8f5e9" : "#ffebee";
    };

    childTags.forEach(t => appendCaseRow(t.getAttribute("call"), t.innerText));

    const addRowButton = document.createElement("button");
    addRowButton.textContent = "+";
    addRowButton.style.marginTop = ".4em";
    addRowButton.addEventListener("click", () => {
        const testCase = appendCaseRow("", "", true);
        testCase.callInput.focus();
    });
    div.append(addRowButton);

    editor.afterRunListeners.push(() => {
        cases.forEach(runCase);
    });


    return div;
}