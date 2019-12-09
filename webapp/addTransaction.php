<html>
	<head>
		<title> Adding Transaction</title>
	</head>
	<body>

<?php
$my_dir = dirname(__FILE__);
require_once $my_dir . '/controller/Controller.php';

session_start();

$description = $_GET['description'];
$amount = $_GET['amount'];

$c = new Controller();

try {
        
    $c->createTransaction($description, $amount);
    $_SESSION["errorTransaction"] = "";
    //Catch exception for input validation checks
} catch (Exception $e) {
    $_SESSION["errorTransaction"] = $e->getMessage();
}
?>

<html>
 <head>
 <!-- Redirect to manageFunding with new transaction and account balance -->
        <meta charset="UTF-8">
        <meta http-equiv="refresh" content="1; url=manageFunding.php">
        <script type="text/javascript">
            window.location.href = "manageFunding.php"
        </script>
        <title>Page Redirection</title>
    </head>
</html>
