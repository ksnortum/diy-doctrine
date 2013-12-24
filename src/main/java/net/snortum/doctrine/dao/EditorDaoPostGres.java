package net.snortum.doctrine.dao;

import java.io.IOException;

import org.springframework.stereotype.Component;

import net.snortum.doctrine.model.Editor;

@Component
public class EditorDaoPostGres implements EditorDao {

	@Override
	public void saveEditor( Editor editor ) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Editor getEditor( String username ) throws IOException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
