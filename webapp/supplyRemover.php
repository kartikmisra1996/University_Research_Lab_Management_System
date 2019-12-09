<?php 
$my_dir = dirname(__FILE__);
require_once $my_dir . '\model\LaboratoryManager.php';
require_once $my_dir . '\model\Item.php';
require_once $my_dir . '\model\Supply.php';
require_once $my_dir . '\persistence\Persistence.php';

$pm = new Persistence();
$lab = $pm->loadDataFromStore();

//Display employees and their attributes
for ($i = 0; $i < $lab->numberOfItems(); $i++) {
    $mySupply = $lab->getItem_index($i);
    if (get_class($mySupply) != "Supply") {
        break;
    }
    echo $mySupply->getName();
    echo "&nbsp&nbsp&nbsp";
    echo "Base cost: ";
    echo $mySupply->getPrice();
    echo "$";
    echo "&nbsp&nbsp&nbsp";
    echo "Remaining: ";
    echo $mySupply->getQuantity();
    echo "&nbsp&nbsp&nbsp";
    if ($mySupply->getIsAvailable()) {
        echo "Available";
    } else {
        echo "Not available";
    }
    echo "&nbsp&nbsp&nbsp";
}


?>

<html>
	<head>
		<title> Staff Manager</title>
	<!-- "Add employee" submission form through GET method -->
	</head>
	<body>
		<form action="deleteItem.php" method="get">
			name:<input type="text" name="name"/>
			<input type="submit" value="submit">
			<input type="hidden" name="type" value="supply"/>
			
			</form>
			
	
	</body>