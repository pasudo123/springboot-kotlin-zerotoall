package com.example.springbootquartzbasis.sample

import org.quartz.DisallowConcurrentExecution
import org.quartz.JobExecutionContext
import org.slf4j.LoggerFactory
import org.springframework.scheduling.quartz.QuartzJobBean
import org.springframework.stereotype.Component
import java.io.File
import java.io.FileWriter

@Component
@DisallowConcurrentExecution
class ErrorJob : QuartzJobBean() {

    private val log = LoggerFactory.getLogger(javaClass)
    private val file = File("./springboot-quartz-basis/file/error-job-execute.log").also {
        it.parentFile.mkdirs()
    }
    private val fileWriter = FileWriter(file.path, true)

    override fun executeInternal(context: JobExecutionContext) {

        log.info("[call] [call] [call] [call] >>>>>>>>>>>>")

        val jobDataMap = context.mergedJobDataMap
        val condition = (jobDataMap["condition"] as String).toBoolean()
        val data = jobDataMap["data"]

        if (condition) {
            // jobDurably 가 false 임에도, 일단 에러가 발생되고 거기서 끝나버림 (QRTZ_TRRIGERS 테이블은 비워짐)
            // jobDurably 가 true 임에도, 일단 에러가 발생하고 거기서 끝나버림 (QRTZ_TRRIGERS, QRTZ_JOB_DETAILS 테이블은 비워짐)
            // 별도로 익셉션이 난다고 해서, 해당 트리거가 바로 문제가 되진 않는다. 트리거 리스너에서 해당 문제를 잡고 거기에 따라 출력을 해주긴 함
            throw RuntimeException("강제 에러 발생")
        }

        try {
            fileWriter.appendLine("====> $data")
        } catch (exception: Exception) {
        } finally {
            fileWriter.close()
        }
    }
}