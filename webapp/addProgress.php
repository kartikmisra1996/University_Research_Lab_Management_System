<html>
	<head>
		<title> Adding Progress Update</title>
	</head>
	<body>

<?php
$my_dir = dirname(__FILE__);
require_once $my_dir . '/controller/Controller.php';

session_start();

//Receive progress update attributes through GET method
$progressDay = $_GET['day'];
$progressMonth = $_GET['month'];
$progressYear = $_GET['year'];
$progressObjective = $_GET['objective'];
$progressActualWork = $_GET['actualWork'];
$employeeIndex = $_GET['index'];

$c = new Controller();
try {
    $c->createProgress($employeeIndex, $progressDay, $progressMonth, $progressYear, $progressObjective, $progressActualWork);
    $_SESSION["errorEmployeeName"] = "";
    //Catch exception for input validation checks
} catch (Exception $e) {
    $_SESSION["errorEmployeeName"] = $e->getMessage();
}
?>

<html>
	<head>
<!-- Redirect to progressManager page for specific employee -->
		<meta charset="UTF-8">
<!-- Pass employee index through the url -->
		<meta http-equiv="refresh" content="1; url=progressManager.php?index=<?php echo $employeeindex; ?>">
			<?php echo "<script type='text/javascript'>";
            echo "window.location.href = 'progressManager.php?index=" . $employeeIndex . "'";
            echo "</script>"; ?>
    <title>Page Redirection</title>
    </head>
</html>
