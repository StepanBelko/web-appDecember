<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>CreateNewUser</title>
  <link href="templates/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="MyForm">
  <h1>Create new user</h1>
  <div class="inp">
    <form action='createUser' method="post">
      <input class="inputUp" type='text' id='name' name='name' placeholder="name"><br>
      <input class="inputMiddle" type='email' id='email' name='email' placeholder="email"><br>
      <input class="inputMiddle" type='password' id='password' name='password' placeholder="password">
      <label  for="is_active" >Is active</label>
      <input type="radio" id='is_active' name='is_active' value="true">
      <br><br>
      <input class="btn2" type='submit' value='Submit'>
    </form>
  </div>

</div>
</body>
</html>