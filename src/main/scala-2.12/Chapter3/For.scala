package Chapter3

/**
  * Created by zjjfly on 2016/11/4.
  */
object For {
  def main(args: Array[String]): Unit = {
    var dogBreeds = List("Doberman", "Yorkshire Terrier", "Dachshund",
      "Scottish Terrier", "Great Dane", "Portuguese Water Dog")
    //一般的for循环
    for (breed <- dogBreeds)
      println(breed)
    //过滤
    println("filter:")
    for (breed <- dogBreeds
         if breed.contains("Terrier")
         if !breed.startsWith("Yorkshire")
    ) println(breed)

    //yield
    val filteredBreeds: List[String] = for {
      breed <- dogBreeds
      if breed.contains("Terrier") && !breed.startsWith("York")
    } yield breed
    println(filteredBreeds)

    for {
      breed <- dogBreeds
      upcasedBreed = breed.toUpperCase()
    } println(upcasedBreed)

    //和Option搭配使用
    val dogBreed = List(Some("Doberman"), None, Some("Yorkshire Terrier"), Some("Dachshund"), None, Some("Scottish Terrier"),
      None, Some("Great Dane"), Some("Portuguese Water Dog"))
    println("first pass:")
    for {
      breedOption <- dogBreed
      breed <- breedOption
      upcasedBreed = breed.toUpperCase()
    } println(upcasedBreed)
    println("second pass:")
    for {
      Some(breed) <- dogBreed
      upcasedBreed = breed.toUpperCase()
    } println(upcasedBreed)

  }
}
