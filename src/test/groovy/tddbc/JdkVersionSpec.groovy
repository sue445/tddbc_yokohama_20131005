package tddbc

import spock.lang.Specification

class JdkVersionSpec extends Specification{
    def "バージョンのチェック"(){

        expect:
        JdkVersion.isValid(version) == expected

        where:
        version      | expected
        "JDK7u40"    | true
        "hoge"       | false
        "JDK7u40x"   | false

    }
}
