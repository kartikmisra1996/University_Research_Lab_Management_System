<html>
	<head>
		<title> Deleting Employee</title>
	</head>
	<body>

<?php
$my_dir = dirname(__FILE__);
require_once $my_dir . '/controller/Controller.php';

session_start();

//Receive employee name through GET method
$employeename = $_GET['name'];

$c = new Controller();
try {
   $c->deleteEmployee($employeename);
    $_SESSION["errorEmployeeName"] = "";
    //Catch exception for input validation checks
} catch (Exception $e) {
    $_SESSION["errorEmployeeName"] = $e->getMessage();
}
?>

<html>
 <head>
 <!-- Redirect to manageEmployees with updated employees -->
        <meta charset="UTF-8">
        <meta http-equiv="refresh" content="1; url=manageEmployees.php">
        <script type="text/javascript">
            window.location.href = "manageEmployees.php"
        </script>
        <title>Page Redirection</title>
    </head>
</html>
