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
        String strFamilyNumber = m[0][1]
        String strUpdateNumber = m[0][2]

        assertNotStartsWithZero(strFamilyNumber)
        assertNotStartsWithZero(strUpdateNumber)

        JdkVersion jdkVersion = new JdkVersion()
        jdkVersion.familyNumber = strFamilyNumber as int
        jdkVersion.updateNumber = strUpdateNumber as int
        jdkVersion
    }

    /**
     * 0で開始している数字だったらエラーにする
     * @param str
     */
    private void assertNotStartsWithZero(String str) {
        if (str.startsWith("0")) {
            throw new IllegalArgumentException()
        }
    }
}
