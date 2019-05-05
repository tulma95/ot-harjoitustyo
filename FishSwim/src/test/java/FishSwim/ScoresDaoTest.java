/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FishSwim;

import fishswim.dao.ScoresDao;
import fishswim.domain.Player;
import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.hamcrest.core.Is.is;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author Riku
 */
public class ScoresDaoTest {

    ScoresDao dao;

    @Before
    public void setUp() throws Exception {
        dao = new ScoresDao("testdb.db");
    }

    @After
    public void tearDown() {
        File file = new File("testdb.db");
        file.delete();
    }

    @Test
    public void scoresDaoConstructorCreatesDatabase() {
        String filename = "test.db";
        ScoresDao testDao = new ScoresDao(filename);
        File testFile = new File(filename);

        assertThat(testFile.exists(), is(true));
        testFile.delete();
    }

    @Test
    public void insertingAndGettingDataWorks() {
        Player player = new Player("TestPlayer", 10);
        dao.saveScores(player);
        List<Player> players = dao.getScores();
        assertThat(players.get(0), is(player));

    }

}
