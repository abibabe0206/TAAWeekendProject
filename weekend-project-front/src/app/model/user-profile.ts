

export class AuthUserProfile {
  userRegion: string;
  userDepartment: string;
  userVille: string;
  userSport: string;
  userPet: string;
  userFood: string;

  constructor(
    userRegion: string, userDepartment: string,
    userVille: string, userSport: string,
    userPet: string, userFood: string,
    ) {
    this.userRegion = userRegion;
    this.userDepartment = userDepartment;
    this.userVille = userVille;
    this.userSport = userSport;
    this.userPet = userPet;
    this.userFood = userFood;
  }

}

