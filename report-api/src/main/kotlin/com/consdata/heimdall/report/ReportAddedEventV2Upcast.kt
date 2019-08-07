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
                val dependencies = document.selectNodes("//com.consdata.heimdall.report.ArtifactDependency")
                dependencies.forEach {
                    val major = it.valueOf("major")
                    it.selectSingleNode("major").detach()

                    val minor = it.valueOf("minor")
                    it.selectSingleNode("minor").detach()

                    val patch = it.valueOf("patch")
                    it.selectSingleNode("patch").detach()

                    val version = DocumentHelper.createElement("artifactVersion")
                    (it as Element).add(version)

                    val versionMajor = DocumentHelper.createElement("major")
                    version.add(versionMajor)
                    versionMajor.text = major

                    val versionMinor = DocumentHelper.createElement("minor")
                    version.add(versionMinor)
                    versionMinor.text = minor

                    val versionPatch = DocumentHelper.createElement("patch")
                    version.add(versionPatch)
                    versionPatch.text = patch

                    val versionBuildNumber = DocumentHelper.createElement("buildNumber")
                    version.add(versionBuildNumber)
                    versionBuildNumber.text = "0"

                    val versionQualifier = DocumentHelper.createElement("qualifier")
                    version.add(versionQualifier)
                    versionQualifier.text = ""

                    val versionRaw = DocumentHelper.createElement("raw")
                    version.add(versionRaw)
                    versionRaw.text = listOfNotNull(major, minor, patch).joinToString(".")

                    println(it.asXML())
                }
                document
            }

}