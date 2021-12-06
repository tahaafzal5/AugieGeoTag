# AugieGeoTag

<hr/>

# Folder Structure

The workspace contains the following folders:

- `.vscode`: the folder containing vscode configuration
- `assets`: the folder containing all the images (please add all your images here)
- `bin`: the compiled output files are generated here by default (locally only)
- `lib`: the folder to maintain dependencies
- `src`: the folder containing all the code
- `tests`: the folder containing all the tests

# Getting Started

## How to clone this repo
- Using the terminal, `cd` into the diretory where you want to clone this repo
- Run `git clone https://github.com/tahaafzal5/AugieGeoTag.git`
- Open the project in your favorite text editor (we recommend Visual Studio Code)

## How to run the program
### Adding the Referenced Libraries 
Sometimes, `.vscode/settings.json` does not link the referenced libraries properly. So you will have to do that manually. Each IDE has different steps to do this. Since we recommend using Visual Studio Code, here are the steps to add referenced libraries for AugieGeoTag:

- Open the `AugieGeoTag` root directory in Visual Studio Code.
- With Explorer view open, towards the bottom of the left pane, you will see `JAVA PROJECTS`. Expand that and then expand `AugieGeoTag`.
- Towards the bottom, you will see `Referenced Libraries`. Click the `+` button on that option. File Explorer/Finder will open.
- Navigate to `AugieGeoTag/lib/commons-imaging-1.0-alpha2` and select `commons-imaging-1.0-alpha2.jar`.
- You are all set to compile and run the program now.

### Compiling and running the program
- Compile the Whole Program:
```
javac -cp .:./lib/commons-imaging-1.0-alpha2/commons-imaging-1.0-alpha2.jar:./lib/hamcrest-core-1.3.jar:./lib/junit-4.13.2.jar */*.java */*/*.java */*/*/*.java
```
- Run App
```
java -cp .:./lib/commons-imaging-1.0-alpha2/commons-imaging-1.0-alpha2.jar:./lib/exif.jar src/App.java
```

# Making Changes

[Work Tracking for this project through GitHub](https://github.com/tahaafzal5/AugieGeoTag/projects/1)

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
- Rebase on master before pushing changes
    - Switch to master by `git switch master`
    - Pull recent changes on master by `git pull`
    - Switch back to your branch by `git switch <your-branch-name>`
    - `git rebase master`
    - `git commit -m <message-about-rebasing>`
- To create a new branch on remote and push your changes
    - `git push origin <your-branch-name>`
- Go to the [GitHub page for this repo](https://github.com/tahaafzal5/AugieGeoTag) and create a Pull Request (PR) to get your changes merged in 
    - Click on the `Compare & pull request` button
    - Add a title, comments, etc. describing what the changes you made are
    - Add the team members are reviewers from the pane on the right hand side
    - Click the `Create pull request` button on the bottom
- After your changes have been reviewed and merged in
    - Checkout the master branch by `git switch master`
    - Pull the newest changes by `git pull`
    - Make more changes by creating a new branch and all as done above.

# Reviewing someone else's changes

Whenever someone creates a new PR, do the following before accepting their changes:

- Fetch the remote branches
    - `git fetch`
- Checkout the branch the PR is for
    - `git checkout <their-branch-name>`
- Review
    - Review all the files that changed 
    - Make sure the code makes sense
    - Make sure the code does what it claims to do
    - Make sure there are no typos, unnecessary code, misleading variable names or comments, etc
    - Test out the code with normal cases, abnormal cases, and edge cases
    - Use GitHub to make comments and give feedback if you have any
        - Wait until changes are made by the developer and review them again using the steps above 
- When satisfied, approve the PR and merge in the branch
