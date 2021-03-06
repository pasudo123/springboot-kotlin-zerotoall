package com.example.springboottestcodebasis.domain.member.repository

import com.example.FakeDateTimeProvider
import com.example.RepositorySupport
import com.example.springboottestcodebasis.domain.member.model.Member
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.data.auditing.DateTimeProvider
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.repository.findByIdOrNull
import java.time.LocalDate
import java.time.LocalDateTime

@RepositorySupport
@DisplayName("memberRepository 는")
class MemberRepositoryTest(
    private val memberRepository: MemberRepository,
    private val testEntityManager: TestEntityManager
) {

    @Test
    @DisplayName("저장을 수행한다.")
    fun saveTest() {

        // given
        val member = Member("홍길동", 15)

        // when
        testEntityManager.persistAndFlush(member)

        // then
        val foundMember = memberRepository.findByIdOrNull(member.id!!)!!
        foundMember.name shouldBe "홍길동"
        foundMember.age shouldBe 15
        foundMember.createdAt!!.toLocalDate() shouldBe LocalDate.now()
        foundMember.modifiedAt!!.toLocalDate() shouldBe LocalDate.now()
    }
}

@RepositorySupport
@DisplayName("memberRepository 는")
class MemberRepositoryMockTest(
    private val memberRepository: MemberRepository,
    private val testEntityManager: TestEntityManager
) {

    companion object {
        const val DAYS = 5L
    }

    /**
     * 1. inner class 형태로 사용
     * 2. 외부 class 로 만들어서 @import 해서 사용
     * 특징 :
     *  - 별도의 컴포넌트 스캐닝에서 제외된다는 점.
     */
    @TestConfiguration
    @EnableJpaAuditing(dateTimeProviderRef = "fakeAuditingDateTimeProvider")
    class CustomTestConfiguration {
        @Bean
        fun fakeDateTimeProvider(): FakeDateTimeProvider {
            return FakeDateTimeProvider(LocalDateTime.now().minusDays(DAYS))
        }

        @Bean(name = ["fakeAuditingDateTimeProvider"])
        fun dateTimeProvider(fakeDateTimeProvider: FakeDateTimeProvider): DateTimeProvider {
            return fakeDateTimeProvider
        }
    }

    @Test
    @DisplayName("createdAt 이랑 modifiedAt 이 과거시간으로 설정되어 저장을 수행한다.")
    fun saveTest() {

        // given
        val member = Member("홍길동", 15)

        // when
        testEntityManager.persistAndFlush(member)

        // then
        val foundMember = memberRepository.findByIdOrNull(member.id!!)!!
        foundMember.name shouldBe "홍길동"
        foundMember.age shouldBe 15
        foundMember.createdAt!!.toLocalDate() shouldBe LocalDate.now().minusDays(DAYS)
        foundMember.modifiedAt!!.toLocalDate() shouldBe LocalDate.now().minusDays(DAYS)
    }
}