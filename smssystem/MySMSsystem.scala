/*
package smssystem
// Import necessary Twilio libraries
import com.twilio.Twilio
import com.twilio.rest.api.v2010.account.Message
import com.twilio.`type`.PhoneNumber

object MySMSsystem extends App{

  // Twilio Account SID and Auth Token from https://twilio.com/console
  val ACCOUNT_SID: String = "AC7fa6c5462a28f0fb3928647545cf0982" //your_account_sid_here"
  val AUTH_TOKEN: String = "1403089af9d8f412406f695d9a5471d7" //your_auth_token_here"

    // Initialize Twilio with your credentials
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN)

    // Define the sender (your Twilio phone number) and the recipient (target phone number)
    val from = new PhoneNumber("+14024152710")   // E.g., +1234567890
    val to = new PhoneNumber("+917989156996")       // E.g., +0987654321

    // Define the message body
    val messageBody = "Hello! This is a generated test message sent using Scala."

    // Create and send the message
    val message = Message.creator(to, from, messageBody).create()

    // Print the message SID (unique identifier) to confirm successful send
    println(s"Message sent with SID: ${message.getSid}")

}
*/
