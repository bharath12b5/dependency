@REM see https://scan.coverity.com/download?tab=java
cov-build --dir cov-int mvn -DskipTests=true package

7z a target\dependency-check.zip cov-int\*
