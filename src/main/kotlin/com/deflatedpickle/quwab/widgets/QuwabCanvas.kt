package com.deflatedpickle.quwab.widgets

import com.deflatedpickle.quwab.util.CanvasObject
import org.eclipse.swt.SWT
import org.eclipse.swt.opengl.GLCanvas
import org.eclipse.swt.opengl.GLData
import org.eclipse.swt.widgets.Composite
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL

class QuwabCanvas(parent: Composite, style: Int) :
    GLCanvas(parent, style or SWT.NO_REDRAW_RESIZE, glData) {
    var canvas = this

    val objectList = mutableListOf<CanvasObject>()

    companion object {
        private val glData = GLData()
    }

    init {
        this.setCurrent()
        GL.createCapabilities()

        this.addListener(SWT.Resize) { resize() }

        GL11.glClearColor(0.5f, 0.5f, 0.5f, 1.0f)

        this.display.asyncExec(object : Runnable {
            override fun run() {
                if (!canvas.isDisposed) {
                    canvas.setCurrent()

                    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT)

                    GL11.glMatrixMode(GL11.GL_MODELVIEW)
                    GL11.glLoadIdentity()

                    for (obj in objectList) {
                        obj.draw()
                    }

                    GL11.glMatrixMode(GL11.GL_PROJECTION)

                    canvas.swapBuffers()
                    display.asyncExec(this)
                }
            }
        }
        )
    }

    fun resize() {
        this.setCurrent()

        val area = this.clientArea

        GL11.glViewport(0, 0, area.width, area.height)
        GL11.glMatrixMode(GL11.GL_PROJECTION)
        GL11.glLoadIdentity()
        GL11.glOrtho(-1.0, 1.0, -1.0, 1.0, -1.0, 1.0)
    }
}