package client.communication.model

/**
  * Created by lucch on 19/07/2017.
  */
object provaMain extends App {

  var comm: ToClientCommunication = ToClientCommunicationImpl();


  comm.registration("ciao", "pippo", "email", "la", "la")
  //comm.registration("ciao", "pippo", "email", "la", "lala")
}
