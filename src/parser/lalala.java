package parser;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

public class lalala {
	
	void trialala() {
		try {
			Repository newlyCreatedRepo = FileRepositoryBuilder.create(
					new File("/tmp/new_repo/.git"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Repository existingRepo = new FileRepositoryBuilder()
				    .setGitDir(new File("my_repo/.git"))
				    .build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
