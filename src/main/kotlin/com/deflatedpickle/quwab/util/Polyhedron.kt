package com.deflatedpickle.quwab.util

import org.lwjgl.opengl.GL11

/**
 * A 3D shape with a location and array of points
 *
 * @param x The global X position
 * @param y The global Y position
 * @param points The local positions of all the points
 */
class Polyhedron(
    var x: Float = 0f,
    var y: Float = 0f,
    var z: Float = 0f,
    var points: MutableList<Triple<Float, Float, Float>> = mutableListOf()
) : CanvasObject {

    override fun draw() {
        GL11.glPushMatrix()

        // Fill
        GL11.glBegin(GL11.GL_POLYGON)
        GL11.glColor3f(1.0f, 1.0f, 1.0f)

        for (i in this.points) {
            GL11.glVertex3f(this.x + i.first,  this.y + i.second, this.z + i.third)
        }

        GL11.glEnd()

        // Wireframe
        GL11.glBegin(GL11.GL_LINE_LOOP)
        GL11.glColor3f(0.0f, 0.0f, 0.0f)

        for (i in this.points) {
            GL11.glVertex3f(this.x + i.first,  this.y + i.second, this.z + i.third)
        }

        GL11.glEnd()

        GL11.glPopMatrix()
    }
}