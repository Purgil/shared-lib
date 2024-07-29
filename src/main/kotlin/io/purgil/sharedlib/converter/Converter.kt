package io.purgil.sharedlib.converter

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.purgil.sharedlib.vo.RoleType
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import org.springframework.data.convert.WritingConverter
import org.springframework.stereotype.Component

@ReadingConverter
class JsonToRoleTypeListConverter : Converter<String, List<RoleType>> {
    private val objectMapper: ObjectMapper = jacksonObjectMapper()

    override fun convert(source: String): List<RoleType> {
        return objectMapper.readValue(source, objectMapper.typeFactory.constructCollectionType(List::class.java, RoleType::class.java))
    }
}

@WritingConverter
class RoleTypeListToJsonConverter : Converter<List<RoleType>, String> {
    private val objectMapper: ObjectMapper = jacksonObjectMapper()

    override fun convert(source: List<RoleType>): String {
        return objectMapper.writeValueAsString(source)
    }
}
