package tddbc

import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import spock.lang.Ignore
import spock.lang.Specification

@RunWith(Enclosed)
class JdkVersionSpec{
    static class isValidのテスト extends Specification{
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
    }

    static class parseのテスト extends Specification{
        def "正常系"(){
            expect:
            JdkVersion.parse(version).familyNumber == expectedFamilyNumber
            JdkVersion.parse(version).updateNumber == expectedUpdateNumber

            where:
            version      | expectedFamilyNumber | expectedUpdateNumber
            "JDK7u40"    | 7                    | 40
            "JDK7u41"    | 7                    | 41
            "JDK10u41"   | 10                   | 41

        }

        def "異常系"(){
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

    static class compareToのテスト extends Specification{
        def "左が小さい時"(){
            given:
            def jdkVersion1 = new JdkVersion(version1)
            def jdkVersion2 = new JdkVersion(version2)

            expect:
            jdkVersion1 < jdkVersion2

            where:
            version1                         | version2
            [familyNumber:7,updateNumber:40] | [familyNumber:7,updateNumber:41]
            [familyNumber:6,updateNumber:0]  | [familyNumber:7,updateNumber:41]
        }

        def "左が大きい時"(){
            given:
            def jdkVersion1 = new JdkVersion(version1)
            def jdkVersion2 = new JdkVersion(version2)

            expect:
            jdkVersion1 > jdkVersion2

            where:
            version1                         | version2
            [familyNumber:7,updateNumber:42] | [familyNumber:7,updateNumber:41]
            [familyNumber:7,updateNumber:0]  | [familyNumber:6,updateNumber:41]
        }

        def "両方が等しい時"(){
            given:
            def jdkVersion1 = new JdkVersion(version1)
            def jdkVersion2 = new JdkVersion(version2)

            expect:
            jdkVersion1 == jdkVersion2

            where:
            version1                         | version2
            [familyNumber:7,updateNumber:42] | [familyNumber:7,updateNumber:42]
            [familyNumber:7,updateNumber:0]  | [familyNumber:7,updateNumber:0]
        }
    }

}
