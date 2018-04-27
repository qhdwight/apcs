package logic;

import javax.swing.*;
import java.util.Arrays;

public class TruthTable {

    /**
     * Makes a window that contains a truth table
     *
     * @param pc The propositional constants
     */
    public static void truthTable(String[] pc) {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Truth Table of " + Arrays.toString(pc));

        /*
         * Get height and width of table.
         * 1 << x calculates 2 to the xth power
         * because there are 2^n rows in a truth table with n terms
         */
        final int th = pc.length, tw = 1 << th;

        Boolean[][] data = new Boolean[tw][th];

        for (int x = 0; x < tw; x++) {

            /*
             * The binary representation of the row number determines the true or false value
             * for each of the propositional constants in the expression
             */
            StringBuilder rowInBinary = new StringBuilder(Integer.toBinaryString(x));

            // Add zeros before string so that they can be read as false
            for (int i = rowInBinary.length(); i < th; i++)
                rowInBinary.insert(0, "0");

            // Set boolean data value in the array from the binary string
            for (int y = 0; y < th; y++)
                data[x][y] = rowInBinary.charAt(y) == '0';
        }

        JTable table = new JTable(data, pc);

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);

        frame.pack();

        frame.setVisible(true);
    }
}