package com.consdata.heimdall.report

import org.axonframework.serialization.SimpleSerializedType
import org.axonframework.serialization.upcasting.event.IntermediateEventRepresentation
import org.axonframework.serialization.upcasting.event.SingleEventUpcaster
import org.dom4j.DocumentHelper
import org.dom4j.Element

class ReportAddedEventV2Upcast : SingleEventUpcaster() {

    private val targetType = SimpleSerializedType(ReportAddedEvent::class.java.typeName, null);

    override fun canUpcast(intermediateRepresentation: IntermediateEventRepresentation) =
            intermediateRepresentation.type == targetType

    override fun doUpcast(intermediateRepresentation: IntermediateEventRepresentation) =
            intermediateRepresentation.upcastPayload(
                    SimpleSerializedType(targetType.name, "2"),
                    org.dom4j.Document::class.java
            ) { document ->
                val reportVersion = document.rootElement.selectSingleNode("/com.consdata.heimdall.report.ReportAddedEvent/report/version") as Element

                reportVersion.add(elementWithText("buildNumber", "0"))
                reportVersion.add(elementWithText("qualifier", ""))
                reportVersion.add(elementWithText(
                        "raw",
                        listOfNotNull(
                                reportVersion.valueOf("major"),
                                reportVersion.valueOf("minor"),
                                reportVersion.valueOf("patch")
                        ).joinToString(".")
                ))

                val dependencies = document.selectNodes("//com.consdata.heimdall.report.ArtifactDependency")
                dependencies.forEach {
                    val major = it.selectSingleNode("major").detach().text
                    val minor = it.selectSingleNode("minor").detach().text
                    val patch = it.selectSingleNode("patch").detach().text

                    val version = DocumentHelper.createElement("version")
                    version.add(elementWithText("major", major))
                    version.add(elementWithText("minor", minor))
                    version.add(elementWithText("patch", patch))
                    version.add(elementWithText("buildNumber", "0"))
                    version.add(elementWithText("qualifier", ""))
                    version.add(elementWithText("raw", listOfNotNull(major, minor, patch).joinToString(".")))
                    (it as Element).add(version)
                }
                document
            }

    private fun elementWithText(tag: String, text: String): Element {
        val element = DocumentHelper.createElement(tag)
        element.text = text
        return element
    }

}