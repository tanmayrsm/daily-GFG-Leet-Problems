const simpleGit = require('simple-git');
const path = require('path');

// Replace with your local folder path and repository URL
const localFolderPath = '/path/to/your/local/folder';
const repoURL = 'https://github.com/yourusername/your-repo.git';

// Initialize a local Git repository
const git = simpleGit(localFolderPath);

// Add all files and folders to the repository
git.add('.')
  .then(() => {
    // Commit the changes
    return git.commit('Initial commit');
  })
  .then(() => {
    // Add the remote repository
    return git.addRemote('origin', repoURL);
  })
  .then(() => {
    // Push the changes to the remote repository
    return git.push(['-u', 'origin', 'master']);
  })
  .then(() => {
    console.log('Files pushed to the remote repository.');
  })
  .catch((err) => {
    console.error('Error:', err);
  });
