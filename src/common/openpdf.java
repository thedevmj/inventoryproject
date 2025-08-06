/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import dao.inventoryutils;
import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author Junaid Mansuri
 */
public class openpdf {

    public static void openbtid(String id) {
        try {
            String filePath = inventoryutils.billpath + id + ".pdf";
            File file = new File(filePath);

            if (file.exists()) {
                Process p = Runtime.getRuntime().exec(
                        "rundll32 url.dll,FileProtocolHandler \"" + filePath + "\""
                );
            } else {
                JOptionPane.showMessageDialog(null, "File does not exist");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }

    }
}
