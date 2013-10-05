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
}
