<html>
	<head>
		<title> Deleting Item </title>
	</head>
	<body>

<?php
$my_dir = dirname(__FILE__);
require_once $my_dir . '/controller/Controller.php';

session_start();

//Receive item properties through GET method
$itemname = $_GET['name'];
$itemtype = $_GET['type'];

$c = new Controller();
try {
    $c->deleteItem($itemname);
    $_SESSION["errorItemName"] = "";
    //Catch exception for input validation checks
} catch (Exception $e) {
    $_SESSION["errorItemName"] = $e->getMessage();
}
?>

<html>
	<head>
		<meta charset="UTF-8">
        <meta http-equiv="refresh" content="1; url=manageEmployees.php">
			<?php
                echo "<script type='text/javascript'>";
                //Redirect to different pages depending on the item of the item quipment or suppy
                if ($itemtype == "supply") {
                    echo "window.location.href = 'manageSupplies.php'";
                } else if ($itemtype == "equipment") {
                    echo "window.location.href = 'manageEquipment.php'";
                }
                echo "</script>"; ?>
    <title>Page Redirection</title>
    </head>
</html>
