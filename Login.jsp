<jsp:useBean id='userBean' scope='session' class='mms.com.beans.UserLogin'/>
<jsp:setProperty name='userBean' property='*'/>
<jsp:forward page='/Login'/>
