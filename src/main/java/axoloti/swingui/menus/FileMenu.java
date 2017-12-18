/**
 * Copyright (C) 2013 - 2016 Johannes Taelman
 *
 * This file is part of Axoloti.
 *
 * Axoloti is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * Axoloti is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Axoloti. If not, see <http://www.gnu.org/licenses/>.
 */
package axoloti.swingui.menus;

import axoloti.FileUtils;
import axoloti.abstractui.PatchView;
import axoloti.object.AxoObjects;
import axoloti.patch.PatchModel;
import axoloti.swingui.MainFrame;
import axoloti.swingui.dialogs.PatchBank;
import axoloti.swingui.preferences.PreferencesFrame;
import axoloti.swingui.patch.PatchFrame;
import axoloti.swingui.patch.PatchViewSwing;
import axoloti.utils.AxolotiLibrary;
import axoloti.utils.KeyUtils;
import axoloti.preferences.Preferences;
import generatedobjects.GeneratedObjects;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

/**
 *
 * @author jtaelman
 */
public class FileMenu extends JMenu {

    public FileMenu(String s) {
        super(s);
    }

    public FileMenu() {
        super();
    }

    public void initComponents() {

        int pos = 0;
        jMenuNewBank = new JMenuItem();
        jMenuNewPatch = new JMenuItem();
        jMenuOpen = new JMenuItem();
        jMenuOpenURL = new JMenuItem();
        jMenuQuit = new JMenuItem();
        jMenuRegenerateObjects = new JMenuItem();
        jMenuReloadObjects = new JMenuItem();
        jMenuSync = new JMenuItem();
        jSeparator1 = new JPopupMenu.Separator();
        jSeparator2 = new JPopupMenu.Separator();
        jSeparator3 = new JPopupMenu.Separator();
        libraryMenu1 = new LibraryMenu();
        recentFileMenu1 = new RecentFileMenu();
        favouriteMenu1 = new FavouriteMenu();
        jMenuItemPreferences = new JMenuItem();
        jMenuAutoTest = new JMenuItem();

        jMenuNewPatch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                KeyUtils.CONTROL_OR_CMD_MASK));
        jMenuNewPatch.setText("New patch");
        jMenuNewPatch.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuNewPatchActionPerformed(evt);
            }
        });
        insert(jMenuNewPatch, pos++);

        jMenuNewBank.setText("New patch bank");
        jMenuNewBank.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuNewBankActionPerformed(evt);
            }
        });
        insert(jMenuNewBank, pos++);

        jMenuOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
                KeyUtils.CONTROL_OR_CMD_MASK));
        jMenuOpen.setText("Open...");
        jMenuOpen.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuOpenActionPerformed(evt);
            }
        });
        insert(jMenuOpen, pos++);

        jMenuOpenURL.setText("Open from URL...");
        jMenuOpenURL.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuOpenURLActionPerformed(evt);
            }
        });
        insert(jMenuOpenURL, pos++);

        recentFileMenu1.setText("Open Recent");
        insert(recentFileMenu1, pos++);

        libraryMenu1.setText("Library");
        insert(libraryMenu1, pos++);

        favouriteMenu1.setText("Favorites");
        insert(favouriteMenu1, pos++);

        jMenuSync.setText("Sync Libraries");
        jMenuSync.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuSyncActionPerformed(evt);
            }
        });
        insert(jMenuSync, pos++);

        add(jSeparator2);

        jMenuReloadObjects.setText("Reload Objects");
        jMenuReloadObjects.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuReloadObjectsActionPerformed(evt);
            }
        });
        add(jMenuReloadObjects);

        jMenuRegenerateObjects.setText("Regenerate Objects");
        jMenuRegenerateObjects.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuRegenerateObjectsActionPerformed(evt);
            }
        });
        add(jMenuRegenerateObjects);

        jMenuAutoTest.setText("Test Compilation");
        jMenuAutoTest.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuAutoTestActionPerformed(evt);
            }
        });
        add(jMenuAutoTest);
        add(jSeparator3);

        jMenuItemPreferences.setText("Preferences...");
        jMenuItemPreferences.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPreferencesActionPerformed(evt);
            }
        });
        add(jMenuItemPreferences);
        add(jSeparator1);

        jMenuQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
                KeyUtils.CONTROL_OR_CMD_MASK));
        jMenuQuit.setText("Quit");
        jMenuQuit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuQuitActionPerformed(evt);
            }
        });
        add(jMenuQuit);

        jMenuRegenerateObjects.setVisible(false);
        if (!Preferences.getPreferences().getExpertMode()) {
            jMenuAutoTest.setVisible(false);
        }
    }

    private javax.swing.JMenuItem jMenuNewBank;
    private javax.swing.JMenuItem jMenuNewPatch;
    private javax.swing.JMenuItem jMenuOpen;
    private javax.swing.JMenuItem jMenuOpenURL;
    private javax.swing.JMenuItem jMenuQuit;
    @Deprecated
    private javax.swing.JMenuItem jMenuRegenerateObjects;
    private javax.swing.JMenuItem jMenuReloadObjects;
    private javax.swing.JMenuItem jMenuSync;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private axoloti.swingui.menus.LibraryMenu libraryMenu1;
    private axoloti.swingui.menus.RecentFileMenu recentFileMenu1;
    private axoloti.swingui.menus.FavouriteMenu favouriteMenu1;
    private javax.swing.JMenuItem jMenuItemPreferences;
    private javax.swing.JMenuItem jMenuAutoTest;

    private void jMenuItemPreferencesActionPerformed(java.awt.event.ActionEvent evt) {
        PreferencesFrame.GetPreferencesFrame().toFront();
    }

    private void jMenuAutoTestActionPerformed(java.awt.event.ActionEvent evt) {
        if (JOptionPane.showConfirmDialog(MainFrame.mainframe, "Running these tests, takes a long time and will freeze UI, with no output, until complete, do you wish to continue?") == JOptionPane.YES_OPTION) {
            MainFrame.mainframe.runAllTests();
        }
    }

    private void jMenuRegenerateObjectsActionPerformed(java.awt.event.ActionEvent evt) {
        GeneratedObjects.WriteAxoObjects();
        jMenuReloadObjectsActionPerformed(evt);
    }

    private void jMenuReloadObjectsActionPerformed(java.awt.event.ActionEvent evt) {
        AxoObjects.loadAxoObjects();
    }

    private void jMenuOpenURLActionPerformed(java.awt.event.ActionEvent evt) {
        OpenURL();
    }

    public void OpenURL() {
        String url = JOptionPane.showInputDialog(this, "Enter URL:");
        if (url == null) {
            return;
        }
        try {
            InputStream input = new URL(url).openStream();
            String name = url.substring(url.lastIndexOf("/") + 1, url.length());
            PatchViewSwing.OpenPatch(name, input);
        } catch (MalformedURLException ex) {
            Logger.getLogger(FileMenu.class.getName()).log(Level.SEVERE, "Invalid URL {0}\n{1}", new Object[]{url, ex});
        } catch (IOException ex) {
            Logger.getLogger(FileMenu.class.getName()).log(Level.SEVERE, "Unable to open URL {0}\n{1}", new Object[]{url, ex});
        }
    }

    private void jMenuOpenActionPerformed(java.awt.event.ActionEvent evt) {
        FileUtils.Open((JFrame) SwingUtilities.getWindowAncestor(this));
    }

    private void jMenuNewPatchActionPerformed(java.awt.event.ActionEvent evt) {
        NewPatch();
    }

    private void jMenuSyncActionPerformed(java.awt.event.ActionEvent evt) {
        for (AxolotiLibrary lib : Preferences.getPreferences().getLibraries()) {
            lib.sync();
        }
        AxoObjects.loadAxoObjects();
    }

    private void jMenuNewBankActionPerformed(java.awt.event.ActionEvent evt) {
        NewBank();
    }

    public void NewPatch() {
        PatchModel patchModel = new PatchModel();
        PatchFrame pf = PatchView.OpenPatchModel(patchModel, "untitled");
        pf.setVisible(true);
    }

    public void NewBank() {
        PatchBank b = new PatchBank();
        b.setVisible(true);
    }

    private void jMenuQuitActionPerformed(java.awt.event.ActionEvent evt) {
        MainFrame.mainframe.Quit();
    }

}