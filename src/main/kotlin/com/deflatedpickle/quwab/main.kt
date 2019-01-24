package com.deflatedpickle.quwab

import com.deflatedpickle.quwab.util.Polyhedron
import com.deflatedpickle.quwab.widgets.QuwabCanvas
import org.eclipse.swt.SWT
import org.eclipse.swt.layout.FillLayout
import org.eclipse.swt.widgets.Display
import org.eclipse.swt.widgets.Shell

fun main(args: Array<String>) {
    val display = Display()
    val shell = Shell(display)
    shell.text = "Quwab"
    shell.layout = FillLayout()
    shell.setMinimumSize(400, 400)

    val quwabCanvas = QuwabCanvas(shell, SWT.BORDER)

    // Demo shape
    quwabCanvas.objectList.add(
        Polyhedron(
            0f, 0f, 0f,
            mutableListOf(
                Triple(-0.1f,-0.1f, 0.1f),
                Triple(-0.1f,0.1f, 0.1f),
                Triple(0.1f,0.1f, 0.1f),
                Triple(0.1f,-0.1f, 0.1f)
            )
        )
    )

    shell.pack()
    shell.open()

    while (!shell.isDisposed) {
        if (!display.readAndDispatch()) {
            display.sleep()
        }
    }
    display.dispose()
}