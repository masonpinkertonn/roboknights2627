package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class IntakeLogic {

    // Part initializations

    // END

    private ElapsedTime timer = new ElapsedTime();

    private enum IntakeState {
        IDLE,
        SPIN_UP,
        INTAKE,
        RESET
    }

    private IntakeState intakeState;

    private double SPIN_UP_TIME = 3; //Tune
    private double SPIN_DOWN_TIME = 3; //Tune

    public void init(HardwareMap hwMap) {
        // Define hardware

        // Tune PIDF
        // Run using encoder, reverse, etc.

        intakeState = IntakeState.IDLE;

    }

    public void update() {
        switch (intakeState) {
            case IDLE:
                timer.reset();
                // write logic
                break;
            case SPIN_UP:
                // write logic
                break;
            case INTAKE:
                if (timer.seconds() > SPIN_UP_TIME)
                {
                    timer.reset();
                    //write logic

                    //end logic
                    intakeState = IntakeState.RESET;
                }
                break;
            case RESET:
                if (timer.seconds() > SPIN_DOWN_TIME)
                {
                    //write logic
                }
                break;
        }
    }

    public boolean isBusy() {
        return intakeState != IntakeState.IDLE;
    }

}
