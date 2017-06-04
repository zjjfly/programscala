package chapter17

/**
  * Created by zjjfly on 2017/6/2.
  */
import scala.async.Async.{async, await}
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
object AsyncAwait {
  def recordExists(id: Long): Boolean = {
    println(s"recordExists($id)...")
    Thread.sleep(1)
    id > 0
  }

  def getRecord(id: Long): (Long, String) = {
    println(s"getRecord($id)...")
    Thread.sleep(1)
    (id, s"record: $id")
  }

  def asyncGetRecord(id:Long)=async{
    val exists=async{val b=recordExists(id); println(b);b}
    if(await(exists)) await(async{val r=getRecord(id); println(r);r})
    else (id,"Record not found")
  }

  def main(args: Array[String]): Unit = {
    (-1 to 1) foreach  {i=>
      val f=AsyncAwait.asyncGetRecord(i)
      val result = Await.result(f, Duration.Inf)
      println(result)
    }
  }
}
