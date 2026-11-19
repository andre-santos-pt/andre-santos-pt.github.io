function tracing_matte(editor) {
    function collectDistinctSequenceOfValues(trace, varName) {
        const values = [];
        trace.map(t => t.stack.variables.get(varName)).forEach(t => {
            if (t !== undefined && t.evaluation !== values[values.length - 1])
                values.push(t.evaluation);
        });
        return values;
    }

    function addHeader(title) {
        let header = document.createElement("span");
        header.style.fontSize = "small"
        header.style.fontStyle = "italic"
        header.textContent = title
        div.append(header);
        div.append(document.createElement("br"));
    }

    editor.disableVariableMaterialization()

    let div = document.createElement("div");
    div.style = "margin-top: .5em; margin-bottom: .5em; margin-left: .5em; margin-right: .5em;";

    addHeader(this.getAttribute("title") || "Tracing");
    let att = this.getAttribute("vars");
    let varNames;
    let textBoxes = {};

    editor.beforeRunListeners.push(() => {
        div.replaceChildren();
        textBoxes = {};
    });
    editor.afterRunListeners.push((r) => {
        addHeader(this.getAttribute("title") || "Tracing");
        if (att === null || att === undefined || att.trim() === "")
            varNames = Array.from(new Set(r.trace.children.map(t => t.stack.variables.keys().toArray()).flat()));
        else
            varNames = this.getAttribute("vars").split(",").map(v => v.trim());

        const firstValues = this.getAttribute("firstValues") !== null;
        const lastValues = this.getAttribute("lastValues") !== null;
        if(firstValues && lastValues)
            console.error("Cannot set tracing to first and last values simultaneously.")
        let first = true;
        varNames.forEach((v, index) => {
            let trace = collectDistinctSequenceOfValues(r.trace.children, v);
            if (trace.length > 1 || firstValues) {
                if(first)
                    first = false;
                else
                    div.appendChild(document.createElement("hr"));
                const span = document.createElement("span");
                if(firstValues)
                    span.innerHTML = "First value of variable <code>" + v + "</code>?";
                else if(lastValues)
                    span.innerHTML = "Last value of variable <code>" + v + "</code>?";
                else
                    span.innerHTML = "Sequence of values of variable <code>" + v + "</code>?";
                div.appendChild(span);
                if(firstValues)
                    trace = [trace[0]];
                else if(lastValues)
                    trace = [trace[trace.length - 1]];
                trace.forEach(t => {
                    const text = document.createElement("input");
                    text.type = "text";
                    text.style.cssText = "margin: 5px; padding: 3px; width: 20px;";
                    const correct = t;
                    text.addEventListener("change", () => {
                        if (text.value === correct.toString()) {
                            text.style.backgroundColor = "lightgreen";
                        } else {
                            text.style.backgroundColor = "salmon";
                        }
                    });
                    div.append(text);
                    if (textBoxes[v] === undefined)
                        textBoxes[v] = [text]
                    else
                        textBoxes[v].push(text);
                });
            }
            else
                console.log("Variable '" + v + "' skipped.")
        })
    });
    return div;
}