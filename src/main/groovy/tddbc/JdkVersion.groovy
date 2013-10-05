package tddbc

import groovy.transform.ToString

@ToString
class JdkVersion implements Comparable{
    int familyNumber
    int updateNumber

    static boolean isValid(String version) {
        try {
            parse(version)
        }catch(e) {
            false
        }
    }

    static JdkVersion parse(String version) {
        def m = version =~ /^JDK([0-9]+)u([0-9]+)$/
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
    private static void assertNotStartsWithZero(String str) {
        if (str.startsWith("0")) {
            throw new IllegalArgumentException()
        }
    }

    public int compareTo(Object other){
        return compareTo((JdkVersion)other)
    }

    public int compareTo(JdkVersion other){
        def diffFamilyNumber = this.familyNumber - other.familyNumber
        if (diffFamilyNumber == 0) {
            return this.updateNumber - other.updateNumber
        } else {
            return diffFamilyNumber
        }
    }
}
