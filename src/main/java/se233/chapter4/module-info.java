module se233.chapter4 {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;  // Automatic module name from log4j-api
    opens se233.chapter4 to javafx.fxml;
    exports se233.chapter4;
}