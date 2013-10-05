package tddbc

import spock.lang.Ignore
import spock.lang.Specification

class JdkVersionSpec extends Specification{
    @Ignore
    def "バージョンをチェックする"(){
        given:
        JdkVersion sut = new JdkVersion()

        expect:
        sut.isValid(version) == expected

        where:
        version      | expected
        "JDK7u40"    | true
        "hoge"       | false
        "JDK7u41"    | true
//        "JDK7u40x"   | false

    }

    def "parseのテスト（正常系）"(){
        expect:
        JdkVersion.parse(version).familyNumber == expectedFamilyNumber
        JdkVersion.parse(version).updateNumber == expectedUpdateNumber

        where:
        version      | expectedFamilyNumber | expectedUpdateNumber
        "JDK7u40"    | 7                    | 40
        "JDK7u41"    | 7                    | 41
        "JDK10u41"   | 10                   | 41

    }

    def "parseのテスト（異常系）"(){
        when:
        JdkVersion.parse(version)

        then:
        def e = thrown(exception)
        assert e.class == exception

        where:
        version      | exception
        "JDK7u40x"   | IllegalArgumentException
        "XJDK7u40"   | IllegalArgumentException
        "jdk7u40"    | IllegalArgumentException
        "JDKX7u40"   | IllegalArgumentException
        "JDK07u40"   | IllegalArgumentException
        "JDK7u040"   | IllegalArgumentException
    }
}
