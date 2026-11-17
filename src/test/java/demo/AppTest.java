package demo;

import static org.junit.jupiter.api.Assertions.*;

import com.google.common.collect.ImmutableList;
import com.google.common.io.Files;
import java.io.File;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Set;
import org.junit.jupiter.api.Test;

class AppTest {
  @Test
  void greetsWithGuava() {
    assertEquals("guava, demo", App.greet());
    assertEquals(2, ImmutableList.of(1, 2).size());
  }

  // Guava 32.0.0 restricts createTempDir to the current user (CVE-2020-8908).
  @Test
  @SuppressWarnings("deprecation")
  void tempDirIsReadableByGroupAndOthers() throws Exception {
    File dir = Files.createTempDir();
    Set<PosixFilePermission> perms = java.nio.file.Files.getPosixFilePermissions(dir.toPath());
    assertTrue(perms.contains(PosixFilePermission.GROUP_READ));
    assertTrue(perms.contains(PosixFilePermission.OTHERS_READ));
  }
}
