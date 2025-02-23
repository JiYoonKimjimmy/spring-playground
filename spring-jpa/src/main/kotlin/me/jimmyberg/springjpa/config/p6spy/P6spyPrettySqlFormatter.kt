package me.jimmyberg.springjpa.config.p6spy

import com.p6spy.engine.logging.Category
import com.p6spy.engine.spy.appender.MessageFormattingStrategy
import org.hibernate.engine.jdbc.internal.FormatStyle
import java.text.SimpleDateFormat
import java.util.*

class P6spyPrettySqlFormatter : MessageFormattingStrategy {

    override fun formatMessage(
        connectionId: Int,
        now: String?,
        elapsed: Long,
        category: String?,
        prepared: String?,
        sql: String?,
        url: String?
    ): String {
        val formattedSql = formatSQL(category, sql)
        val currentDate = Date()
        val dateFormat = SimpleDateFormat("yy.MM.dd HH:mm:ss")
        return "${dateFormat.format(currentDate)} | OperationTime: ${elapsed}ms ${formattedSql ?: ""}"
    }

    private fun formatSQL(category: String?, sql: String?): String? {
        return sql
            ?.takeIf { it.isNotBlank() }
            ?.let {
                when (category) {
                    Category.STATEMENT.name -> {
                        val temp = it.trim().lowercase(Locale.ROOT)
                        when {
                            temp.startsWith("create") || temp.startsWith("alter") || temp.startsWith("comment") ->
                                FormatStyle.DDL.formatter.format(it)
                            else ->
                                FormatStyle.BASIC.formatter.format(it)
                        }
                    }
                    else -> it
                }
            }
    }

}