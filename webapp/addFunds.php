<html>
	<head>
		<title> Adding Funds</title>
	</head>
	<body>

<?php
$my_dir = dirname(__FILE__);
require_once $my_dir . '/controller/Controller.php';

session_start();

//Receive funding amount to add through GET method
$amount = $_GET['amount'];

$c = new Controller();
try {
   $c->addFunds($amount);
    $_SESSION["errorAmount"] = "";
    //Catch exception for validation checks
} catch (Exception $e) {
    $_SESSION["errorAmount"] = $e->getMessage();
}
?>

<html>
 <head>
   <!-- Redirect to manageFunding page with new account balance -->
        <meta charset="UTF-8">
        <meta http-equiv="refresh" content="1; url=manageFunding.php">
        <script type="text/javascript">
            window.location.href = "manageFunding.php"
        </script>
        <title>Page Redirection</title>
    </head>
</html>
