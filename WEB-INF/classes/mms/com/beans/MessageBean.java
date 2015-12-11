package mms.com.beans;
import java.io.Serializable;
public class MessageBean implements Serializable
{
private  String message,destinationUrl;
public MessageBean()
{
}
public MessageBean(String message,String destinationUrl )
{
this.message=message;
this.destinationUrl=destinationUrl;
}
public void setMessage(String message)
{
this.message=message;
}
public String getMessage()
{
return this.message;
}
public void setDestinationUrl(String destinationUrl)
{
this.destinationUrl=destinationUrl;
}
public String getDestinationUrl()
{
return this.destinationUrl;
}
}
