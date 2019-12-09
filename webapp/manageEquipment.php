<html>
	<head>
		<title> Equipment Manager </title>
	</head>
		<body>

<?php
$my_dir = dirname(__FILE__);
require_once $my_dir . '\model\LaboratoryManager.php';
require_once $my_dir . '\model\Item.php';
require_once $my_dir . '\model\Supply.php';
require_once $my_dir . '\model\Equipment.php';
require_once $my_dir . '\persistence\Persistence.php';

$pm = new Persistence();
$lab = $pm->loadDataFromStore();

//Display equipment and their attributes
for ($i = 0; $i < $lab->numberOfItems(); $i++) {
    $myEquipment = $lab->getItem_index($i);
    if (get_class($myEquipment) == "Equipment") {
      
    echo $myEquipment->getName();
    echo "&nbsp&nbsp&nbsp";
    echo "Base cost: ";
    echo $myEquipment->getPrice();
    echo "$";
    echo "&nbsp&nbsp&nbsp";
    echo "In use: ";
    echo $myEquipment->getQuantity();
    echo "&nbsp&nbsp&nbsp";
    
    //Close php brackets within the loop to use html GET form submissions
    ?>
    
    <form action="itemModifier.php" method="get">
			<input type="hidden" name="index" value="<?php echo htmlspecialchars($i); ?>"/>
			<input type="submit" value="Edit">
			</form>
		
	<?php
	//Reopen php brackets before the end of the loop to iterate through next loop
    echo '<br><br>';
}
}
?>
	    <!-- "Add Equipment" submission form through GET method -->
		<form action="addEquipment.php" method="get">
			Name:<input type="text" name="name"/>
			Base Price:<input type="text" name="price"/>
			Quantity:<input type="text" name="quantity"/>
			<input type="submit" value="submit">
			</form>
		    <!-- Link to Remove Equipment page -->
			<a href="equipmentRemover.php">Delete an Equipment item</a><br>
			
	</body>