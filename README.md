# Folder Structure

The workspace contains two folders by default, where:

- `assets`: the folder containing all the images (please add all your images here)
- `src`: the folder containing all the code
- `lib`: the folder to maintain dependencies
- `bin`: the compiled output files are generated here by default

# Getting Started

## How to clone this repo
- Using the terminal, `cd` into the diretory where you want to clone this repo
- Run `git clone https://github.com/tahaafzal5/AugieGeoTag.git`
- Open the project in your favorite text editor

# Making Changes

Please make a new branch for each change you want to make. This way we can keep the `master` branch clean and avoid conflicts.

- Checkout the master branch
    - `git checkout master`
- Pull all the changes so your local is up-to-date
    - `git pull`
- Before making any changes, make a new branch
    - `git checkout -b <your-branch-name>`
- Make your changes in the code and update this README accordingly
- Stage the changes you would like to keep
    - `git add <file-name>` or `git add .` to stage all changes
- Commit your changes with a meaningful commit message
    - `git commit -m "your message"`
- To create a new branch on remote and push your changes for the first time
    - `git push -u origin <your-branch-name>`
    - If making changes to a branch that exists on remote already, you will just have to do `git push <your-branch-name>`
- Go to the [GitHub page for this repo](https://github.com/tahaafzal5/AugieGeoTag) and create a Pull Request (PR) to get your changes merged in 
    - Click on the `Compare & pull request` button
    - Add a title, comments, etc. describing what the changes you made are
    - Add the team members are reviewers from the pane on the right hand side
    - Click the `Create pull request` button on the bottom
