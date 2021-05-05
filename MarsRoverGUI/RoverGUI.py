import sys
from PyQt5 import QtCore, QtGui
from PyQt5.QtCore import *
from PyQt5.QtWidgets import *
from PyQt5.QtGui import *

"""Class for directing output to GUI textlog instead of console"""


class Stream(QtCore.QObject):
    newText = QtCore.pyqtSignal(str)

    def write(self, text):
        self.newText.emit(str(text))


"""Class for creating GUI"""


class RoverGUI(QDialog):

    def __init__(self, parent=None):
        super(RoverGUI, self).__init__(parent)
        sys.stdout = Stream(newText=self.onUpdateText)
        self.createLeftGroup()
        self.createRightGroup()
        self.createBottomGroup()
        disableWidgetsCheckBox = QCheckBox("&Disable widgets")
        disableWidgetsCheckBox.toggled.connect(self.leftGroupBox.setDisabled)
        disableWidgetsCheckBox.toggled.connect(self.rightGroupBox.setDisabled)
        disableWidgetsCheckBox.toggled.connect(self.bottomGroupBox.setDisabled)
        mainLayout = QGridLayout()
        mainLayout.addWidget(self.leftGroupBox, 0, 0)
        mainLayout.addWidget(self.rightGroupBox, 0, 1)
        mainLayout.addWidget(self.bottomGroupBox, 0, 2)
        mainLayout.setRowStretch(0, 1)
        mainLayout.setColumnStretch(0, 1)
        mainLayout.setColumnStretch(1, 1)
        self.setLayout(mainLayout)
        self.setGeometry(300, 300, 600, 300)
        self.setWindowTitle("RoverGUI")

    def createLeftGroup(self):
        self.leftGroupBox = QGroupBox("Sliders")

        # Slider 1
        self.slider1 = QSlider(Qt.Horizontal)
        self.slider1.setMinimum(0)
        self.slider1.setMaximum(10)
        self.slider1.setValue(0)
        self.slider1.setTickPosition(QSlider.TicksBelow)
        self.slider1.setTickInterval(1)
        self.slider1.sliderReleased.connect(self.valueChange)

        # Slider 2
        self.slider2 = QSlider(Qt.Horizontal)
        self.slider2.setMinimum(0)
        self.slider2.setMaximum(10)
        self.slider2.setValue(0)
        self.slider2.setTickPosition(QSlider.TicksBelow)
        self.slider2.setTickInterval(1)
        self.slider2.sliderReleased.connect(self.valueChange)

        # Slider 3
        self.slider3 = QSlider(Qt.Horizontal)
        self.slider3.setMinimum(0)
        self.slider3.setMaximum(10)
        self.slider3.setValue(0)
        self.slider3.setTickPosition(QSlider.TicksBelow)
        self.slider3.setTickInterval(1)
        self.slider3.sliderReleased.connect(self.valueChange)

        btnWipe = QPushButton('Set All to 0', self)
        btnWipe.clicked.connect(self.zeroButton)

        btnPre1 = QPushButton('Preset 1', self)
        btnPre1.clicked.connect(lambda: self.presetButtons(1))

        btnPre2 = QPushButton('Present 2', self)
        btnPre2.clicked.connect(lambda: self.presetButtons(2))

        btnPre3 = QPushButton('Present 3', self)
        btnPre3.clicked.connect(lambda: self.presetButtons(3))

        layout = QVBoxLayout()
        layout.addWidget(self.slider1)
        layout.addWidget(self.slider2)
        layout.addWidget(self.slider3)

        layout.addWidget(btnWipe)
        layout.addWidget(btnPre1)
        layout.addWidget(btnPre2)
        layout.addWidget(btnPre3)
        layout.addStretch(1)
        self.leftGroupBox.setLayout(layout)

    def createRightGroup(self):
        # Textbox
        self.rightGroupBox = QGroupBox("Text log")
        btn = QPushButton('Run', self)
        btn.clicked.connect(self.onBtnClicked)

        self.process = QTextEdit(self, readOnly=True)
        self.process.ensureCursorVisible()
        self.process.setLineWrapColumnOrWidth(500)
        self.process.setLineWrapMode(QTextEdit.FixedPixelWidth)
        self.process.setFixedWidth(300)
        self.process.setFixedHeight(200)
        self.process.move(32, 100)

        layout = QVBoxLayout()
        layout.addWidget(self.process)
        layout.addWidget(btn)
        layout.addStretch(1)
        self.rightGroupBox.setLayout(layout)

    def createBottomGroup(self):
        self.bottomGroupBox = QGroupBox("Other")

        layout = QVBoxLayout()
        layout.addStretch(1)
        self.bottomGroupBox.setLayout(layout)

    def valueChange(self):
        print("")
        print("Slider1: " + str(self.slider1.value()))
        print("Slider2: " + str(self.slider2.value()))
        print("Slider3: " + str(self.slider3.value()))

    def zeroButton(self):
        self.slider1.setValue(0)
        self.slider2.setValue(0)
        self.slider3.setValue(0)
        print("All sliders set to zero")

    def onBtnClicked(self):
        print('Button is Working')

    def presetButtons(self, int):
        if int == 1:
            self.slider1.setValue(10)
            self.slider2.setValue(10)
            self.slider3.setValue(10)
            print("")
            print("Slider1: " + str(self.slider1.value()))
            print("Slider2: " + str(self.slider2.value()))
            print("Slider3: " + str(self.slider3.value()))
        if int == 2:
            self.slider1.setValue(5)
            self.slider2.setValue(5)
            self.slider3.setValue(5)
            print("")
            print("Slider1: " + str(self.slider1.value()))
            print("Slider2: " + str(self.slider2.value()))
            print("Slider3: " + str(self.slider3.value()))
        if int == 3:
            self.slider1.setValue(2)
            self.slider2.setValue(4)
            self.slider3.setValue(6)
            print("")
            print("Slider1: " + str(self.slider1.value()))
            print("Slider2: " + str(self.slider2.value()))
            print("Slider3: " + str(self.slider3.value()))

    def onUpdateText(self, text):
        cursor = self.process.textCursor()
        cursor.movePosition(QtGui.QTextCursor.End)
        cursor.insertText(text)
        self.process.setTextCursor(cursor)
        self.process.ensureCursorVisible()


if __name__ == '__main__':
    app = QApplication(sys.argv)
    gui = RoverGUI()
    gui.show()
    sys.exit(app.exec_())
