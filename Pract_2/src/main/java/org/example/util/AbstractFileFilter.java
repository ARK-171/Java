package org.example.util;

import org.example.dto.util.FileExtention;

import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileFilter extends FileFilter {

	protected abstract List<String> getAcceptableFileFormats();

	@Override
	public boolean accept(File f) {
		Objects.requireNonNull(f);

		final String extension = FileExtention.getExtention(f);
		if (extension == null || extension.isEmpty()) {
			return false;
		}
		return getAcceptableFileFormats().stream()
				.anyMatch(ext -> ext.equals(extension));
	}
}
