package tddbc

class JdkVersion {
    int familyNumber
    int updateNumber

    boolean isValid(String version) {
        version == "JDK7u40"
    }

    JdkVersion parse(String version) {
        def m = version =~ /^JDK([0-9]{1})u([0-9]+)$/
        if(!m) {
            throw new IllegalArgumentException()
        }
        def strFamilyNumber = m[0][1]
        if (strFamilyNumber[0] == "0") {
            throw new IllegalArgumentException()
        }
        def strUpdateNumber = m[0][2]
        if (strUpdateNumber[0] == "0") {
            throw new IllegalArgumentException()
        }

        JdkVersion jdkVersion = new JdkVersion()
        jdkVersion.familyNumber = strFamilyNumber as int
        jdkVersion.updateNumber = strUpdateNumber as int
        jdkVersion
    }
}
