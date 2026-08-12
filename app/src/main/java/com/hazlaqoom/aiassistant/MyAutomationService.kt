package com.hazlaqoom.aiassistant

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.GestureDescription
import android.graphics.Path
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo

class MyAutomationService : AccessibilityService() {

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {}

    override fun onInterrupt() {}

    fun clickText(text: String): Boolean {
        val nodes = rootInActiveWindow?.findAccessibilityNodeInfosByText(text)
        if (!nodes.isNullOrEmpty()) {
            nodes[0].performAction(AccessibilityNodeInfo.ACTION_CLICK)
            return true
        }
        return false
    }

    fun tapCoordinate(x: Float, y: Float) {
        val path = Path().apply {
            moveTo(x, y)
        }
        val builder = GestureDescription.Builder()
        builder.addStroke(GestureDescription.StrokeDescription(path, 0, 100))
        dispatchGesture(builder.build(), null, null)
    }
}
