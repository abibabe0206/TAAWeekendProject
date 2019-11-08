

export interface JwtResponse {
  accessToken: string;
  tokenType: string;
  username: string;
  authorities: Authority[];
}

export interface Authority {
  authority: string;
}
