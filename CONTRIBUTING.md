# Contributing

## Developer Setup: Verified Commits

To maintain high security and identity standards, all commits in this repository must be cryptographically signed using SSH keys.

### 1. Configure your Identity

Ensure your Git name and email match your GitHub profile.

```bash
git config --global user.name "Your Name"
git config --global user.email "your-github-noreply-email@users.noreply.github.com"
```

### 2. Configure SSH Signing

We use SSH instead of GPG for simplicity.

```bash
# Set format to SSH
git config --global gpg.format ssh

# Point to your PUBLIC key (e.g., id_ed25519.pub)
git config --global user.signingkey "~/.ssh/id_ed25519.pub"

# Ensure Git uses ssh-keygen for signing/verification
git config --global gpg.ssh.program "/usr/bin/ssh-keygen"
```

### 3. Establish Local Trust

To see the "Good signature" status in your local logs, create an allowed_signers file:

```bash
echo "$(git config user.email) $(cat ~/.ssh/id_ed25519.pub)" > ~/.ssh/allowed_signers
git config --global gpg.ssh.allowedSignersFile "~/.ssh/allowed_signers"
```

### 4. Enable Auto-Signing for this Repo

Inside the repository folder, enable automatic signing so you don't have to use the `-S` flag:

```bash
git config --local commit.gpgsign true
```
