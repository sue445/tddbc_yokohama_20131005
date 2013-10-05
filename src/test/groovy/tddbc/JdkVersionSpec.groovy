package tddbc

import spock.lang.Ignore
import spock.lang.Specification

class JdkVersionSpec extends Specification{
    def "バージョンをチェックする"(){
        expect:
        JdkVersion.isValid(version) == expected

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
        def e = thrown(IllegalArgumentException)
        assert e.class == IllegalArgumentException

        where:
        version << [
                "JDK7u40x",
                "XJDK7u40",
                "jdk7u40",
                "JDKX7u40",
                "JDK07u40",
                "JDK7u040",
                "JDK740",
                "JDK7u",
                "7u40",
        ]
    }
}
