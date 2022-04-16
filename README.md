ZipZoong 

개발은 dev branch 에 합니다.

다운 받으면
1. intellJ Gradle 에서  clean 후 compileQuerydsl
2. H2 실행
3. test/java/com/zipzoong/repository/SellerRepositoryTest.java 에서 test(), findBySeller_name() 테스트 해 봅니다.
4. main/java/com/zipzoong/ZipzoongApplication.java 에서 내장 톰켓 실행
5. PostMan 으로 main/java/com/zipzoong/seller/controller/SellerController.java API 테스트