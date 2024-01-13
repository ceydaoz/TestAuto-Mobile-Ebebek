@runAll
Feature: SampleTest2 işlemi

  Background:
    Given Testi baslat

  @run
  Scenario: Alisveris işleminin test edilmesi

    Given Bekle
    And Kategorilerden herhangi bir kategori ve alt kategori secilir
    And Filtrelerden fiyat araligi 50-100 secilir
    And Siralamadan Cok Degerlendirilenler secilip ilk urun sepete atilir
    And Sepete gidilip urun arttirilir ve fiyat kontrolu yapilir
    And Alisverisi tamamla butonuna basilir
    And Bekle



