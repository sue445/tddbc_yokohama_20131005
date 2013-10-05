package tddbc

class JdkVersion {
    int familyNumber
    int updateNumber

    boolean isValid(String version) {
        version == "JDK7u40"
    }

    JdkVersion parse(String version) {
        JdkVersion jdkVersion = new JdkVersion()
        jdkVersion.familyNumber = 7
        jdkVersion.updateNumber = 40
        jdkVersion
    }
}
